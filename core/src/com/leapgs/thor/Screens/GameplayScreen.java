package com.leapgs.thor.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.leapgs.thor.Actors.AngleArrowActor;
import com.leapgs.thor.Actors.HammerActor;
import com.leapgs.thor.Actors.StaticImageActor;
import com.leapgs.thor.Actors.TargetActor;
import com.leapgs.thor.InputProcessors.GameplayInputProcessor;
import com.leapgs.thor.MainGame;
import com.leapgs.thor.Models.LevelData;
import com.leapgs.thor.Models.Target;

/**
 * Created by Leap-Pancho on 6/21/2017.
 */

public class GameplayScreen extends BaseScreen {

    private LevelData currentLevelData;
    private Array<Target> currentTargets;

    private int currentLevel,numberOfTargets;
    private boolean won;
    private float currentPoints, currentTimeLeft;

    private Group foregroundGroup,backgroundGroup;

    private GameplayInputProcessor gameplayInputProcessor;

    Label timeLeftLabel;
    private Label.LabelStyle style;

    private HammerActor hammer;

    private StaticImageActor angleMeter;
    private AngleArrowActor angleArrow;

    public boolean targetDestroyed;

    public GameplayScreen(MainGame mainGame, int level) {
        super(mainGame);
        currentLevel = level;

        style = new Label.LabelStyle();
        style.font=game.font;
        style.fontColor= Color.BLACK;

        timeLeftLabel = new Label("",style);

        won = false;

        foregroundGroup = new Group();
        backgroundGroup = new Group();

        getLevelData(currentLevel);

        gameplayInputProcessor = new GameplayInputProcessor(this);

        targetDestroyed=false;
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameplayInputProcessor);


        addLabelToStage(timeLeftLabel,200,375,150,25,Color.BLACK);

        angleMeter = new StaticImageActor(game,"sprites/blueStuff.png",50,200);
        angleArrow = new AngleArrowActor(62.5f,200);

        hammer = new HammerActor(this,25,200);

        angleMeter.setVisible(false);

        foregroundGroup.addActor(angleMeter);
        foregroundGroup.addActor(angleArrow);
        foregroundGroup.addActor(hammer);

        spawnNewTarget();

        gameplayGroup.addActorAt(0,backgroundGroup);
        gameplayGroup.addActorAt(1, foregroundGroup);

    }

    private void getLevelData(int currentLevel) {

        FileHandle file = Gdx.files.local("levels/level"+currentLevel+".json");
        String levelString = file.readString();
        Json json = new Json();
        currentLevelData = json.fromJson(LevelData.class,levelString);
        currentTimeLeft = currentLevelData.getLevelTime();
        numberOfTargets = currentLevelData.getNumberOfTargets();
        currentTargets = currentLevelData.getTargets();
    }

    public void spawnNewTarget()
    {
        if(currentTargets.size==0)
        {
            won=true;
            endGame();
        }
        else
        {
            addTargetActorToStage(currentTargets.get(0));
            currentTargets.removeIndex(0);
        }
    }

    private void addTargetActorToStage(Target target) {

        foregroundGroup.addActor(new TargetActor(this,target.getPosX(),target.getPosY(),target.getMovementAmplitude(),target.getMovementType(),hammer));
        targetDestroyed=false;
    }

    private void addLabelToStage(Label label, float x , float y , float width , float height , Color color) {
        label.setAlignment(Align.center);
        label.setColor(color);
        label.setSize(width,height);
        label.setPosition(x-width/2,y-height/2);
        overlayGroup.addActor(label);
    }

    public void launchHammer()
    {
        hammer.launch(angleArrow.getCurrentAngle());
        angleMeter.setVisible(false);
        angleArrow.setVisible(false);
    }

    public void targetDestroyed()
    {
        targetDestroyed=true;
        hammer.goBack();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        currentTimeLeft = currentTimeLeft - delta;
        timeLeftLabel.setText("TIME LEFT = "+ currentTimeLeft);

        if(currentTimeLeft <=0)
        {
            won=false;
            endGame();
        }

    }

    private void endGame() {
        game.scorePrefs.putFloat("level"+currentLevel+"starts",calculateStarRating());

        game.goToResultsScreen(currentLevel,won);
        game.scorePrefs.flush();

    }

    private int calculateStarRating() {
        int stars;
        float playtime = currentLevelData.getLevelTime() - currentTimeLeft;
        if(playtime<=currentLevelData.getStarTime3())
        {
            stars=3;
        }
        else
        {
            if(playtime<=currentLevelData.getStarTime2())
            {
                stars=2;
            }
            else
            {
                if(playtime<=currentLevelData.getStarTime1())
                {
                    stars=1;
                }
                else {
                    stars=0;
                }
            }
        }
        return stars;
    }


    public void startAngleCount() {
        angleMeter.setVisible(true);
        angleArrow.starRotating();
    }
}