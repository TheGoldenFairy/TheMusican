package TheMusician.Events;

import TheMusician.MusicianMod;
import TheMusician.Relics.MusicalCrowd;
import TheMusician.Relics.MusicalDestiny;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static TheMusician.MusicianMod.makeEventPath;

public class TheMusicianChallengeEvent extends AbstractImageEvent {

    //~~~~~~~~~~~~~~~~~~ Event Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = MusicianMod.makeID("TheMusicianChallengeEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("TheMusicianChallengeEvent.png");


    //~~~~~~~~~~~~~~~~~~ Variables to be used ~~~~~~~~~~~~~~~~~~//
    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;
    AbstractRelic Destiny = new MusicalDestiny();
    AbstractRelic Crowd = new MusicalCrowd();


    //~~~~~~~~~~~~~~~~~~ Constructor ~~~~~~~~~~~~~~~~~~//
    public TheMusicianChallengeEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);

        imageEventText.setDialogOption(OPTIONS[0]); //To Take the Challenge and Gain 2 Relic's
        imageEventText.setDialogOption(OPTIONS[1]); //To Avoid the Challenge
    }


    //~~~~~~~~~~~~~~~~~~ Event ~~~~~~~~~~~~~~~~~~//
    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {

            //============ The First Page of the Event ============//
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {

                    //============ Used to receive the 2 Relic's ============//
                    case 0:
                        imageEventText.updateBodyText(DESCRIPTIONS[1]); // Update the text of the event
                        imageEventText.updateDialogOption(0, OPTIONS[2]); // 1. Change the first button to the [Leave] button
                        imageEventText.clearRemainingOptions(); // 2. and remove all others
                        screenNum = 1; // Screen set the screen number break;
                        if(!(AbstractDungeon.player.hasRelic(Destiny.relicId)) || !(AbstractDungeon.player.hasRelic(Crowd.relicId))) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2, (float)Settings.HEIGHT / 2, Destiny);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2, (float)Settings.HEIGHT / 2, Crowd);
                        }
                        break;

                    //============ Use to Leave the Event ============//
                    case 1:
                        imageEventText.updateBodyText(DESCRIPTIONS[2]); // Update the text of the event
                        imageEventText.updateDialogOption(0, OPTIONS[2]); // 1. Change the first button to the [Leave] button
                        imageEventText.clearRemainingOptions(); // 2. and remove all others
                        screenNum = 1;
                        break;
                }

                break;

            //============ The Last Page of the Event ============//
            case 1:
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }

}
