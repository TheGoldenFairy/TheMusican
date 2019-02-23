package TheMusician;

import TheMusician.Cards.*;
import TheMusician.Characters.TheMusician;
import TheMusician.Encounters.MusicalLover;
import TheMusician.Patches.CustomTags;
import TheMusician.Relics.MusicalAward;
import TheMusician.Relics.MusicalCrowd;
import TheMusician.Relics.MusicalDestiny;
import TheMusician.Relics.MusicalInstrument;
import TheMusician.Variables.MusicianCustomVariable;
import TheMusician.Variables.MusicianSecondMagicNumber;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import TheMusician.Util.TextureLoader;


@SpireInitializer
public class MusicianMod implements EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(MusicianMod.class.getName());
    private static String modID;

    //~~~~~~~~~~~~~~~~~~~~ This is for the in-game mod settings panel. ~~~~~~~~~~~~~~~~~~~~//
    private static final String MODNAME = "The Musician";
    private static final String AUTHOR = "The Golden Fairy";
    private static final String DESCRIPTION = "Adds a new class to the game, The Musician.";


    //~~~~~~~~~~~~~~~~~~~~ Images ~~~~~~~~~~~~~~~~~~~~//
    private static final String BADGE_IMAGE = "TheMusicianResources/images/Badge.png";

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_MUSICIAN_GOLD = "TheMusicianResources/images/512/bg_attack_musician_gold.png";
    private static final String SKILL_MUSICIAN_GOLD = "TheMusicianResources/images/512/bg_skill_musician_gold.png";
    private static final String POWER_MUSICIAN_GOLD = "TheMusicianResources/images/512/bg_power_musician_gold.png";

    private static final String ENERGY_ORB_MUSICIAN_GOLD = "TheMusicianResources/images/512/card_musician_gold_orb.png";
    private static final String CARD_ENERGY_ORB = "TheMusicianResources/images/512/card_small_orb.png";

    private static final String ATTACK_MUSICIAN_GOLD_PORTRAIT = "TheMusicianResources/images/1024/bg_attack_musician_gold.png";
    private static final String SKILL_MUSICIAN_GOLD_PORTRAIT = "TheMusicianResources/images/1024/bg_skill_musician_gold.png";
    private static final String POWER_MUSICIAN_GOLD_PORTRAIT = "TheMusicianResources/images/1024/bg_power_musician_gold.png";
    private static final String ENERGY_ORB_MUSICIAN_GOLD_PORTRAIT = "TheMusicianResources/images/1024/card_musician_gold_orb.png";

    // Character assets
    private static final String THE_MUSICIAN_BUTTON = "TheMusicianResources/images/charSelect/MusicianCharacterButton.png";
    private static final String THE_MUSICIAN_PORTRAIT = "TheMusicianResources/images/charSelect/MusicianCharacterPortraitBG.png";
    public static final String THE_MUSICIAN_SHOULDER_1 = "TheMusicianResources/images/char/MusicianCharacter/shoulder.png";
    public static final String THE_MUSICIAN_SHOULDER_2 = "TheMusicianResources/images/char/MusicianCharacter/shoulder2.png";
    public static final String THE_MUSICIAN_CORPSE = "TheMusicianResources/images/char/MusicianCharacter/corpse.png";

    // Atlas and JSON files for the Animations
    public static final String THE_MUSICIAN_SKELETON_ATLAS = "TheMusicianResources/images/char/MusicianCharacter/skeleton.atlas";
    public static final String THE_MUSICIAN_SKELETON_JSON = "TheMusicianResources/images/char/MusicianCharacter/skeleton.json";


    //~~~~~~~~~~~~~~~~~~~~ Getting the New Color for the Musician ~~~~~~~~~~~~~~~~~~~~//
    public static final Color MUSICIAN_GOLD = CardHelper.getColor(255.0f,215.0f,0.0f);


    //~~~~~~~~~~~~~~~~~~~~ Variables to be used ~~~~~~~~~~~~~~~~~~~~//
    public static CardGroup MusicalGroup;


    //~~~~~~~~~~~~~~~~~~~~ Initializing the Mod ~~~~~~~~~~~~~~~~~~~~//
    private MusicianMod() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);
        setModID("TheMusician");

        BaseMod.addColor(TheMusician.Enums.COLOR_GOLD, MUSICIAN_GOLD, MUSICIAN_GOLD, MUSICIAN_GOLD,
                MUSICIAN_GOLD, MUSICIAN_GOLD, MUSICIAN_GOLD, MUSICIAN_GOLD,
                ATTACK_MUSICIAN_GOLD, SKILL_MUSICIAN_GOLD, POWER_MUSICIAN_GOLD, ENERGY_ORB_MUSICIAN_GOLD,
                ATTACK_MUSICIAN_GOLD_PORTRAIT, SKILL_MUSICIAN_GOLD_PORTRAIT, POWER_MUSICIAN_GOLD_PORTRAIT,
                ENERGY_ORB_MUSICIAN_GOLD_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done subscribing");
    }

    public static void initialize() {
        logger.info("Initializing Reaper Mod");
        new MusicianMod();
    }


    //~~~~~~~~~~~~~~~~~~~~ Paths for all the Images ~~~~~~~~~~~~~~~~~~~~//
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeEnemyPath(String resourcePath) {
        return getModID() + "Resources/images/enemies/" + resourcePath;
    }


    //~~~~~~~~~~~~~~~~~~~~ Setting ID values ~~~~~~~~~~~~~~~~~~~~//
    private static void setModID(String ID) {
        modID = ID;
    }

    private static String getModID() {
        return modID;
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }


    //~~~~~~~~~~~~~~~~~~~~ Path Checking ~~~~~~~~~~~~~~~~~~~~//
    private static void pathCheck() {
        String packageName = MusicianMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
    }


    //~~~~~~~~~~~~~~~~~~~~ Receive Functions ~~~~~~~~~~~~~~~~~~~~//
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");

        logger.info("Adding Monsters");

        InitializeMonsters();

        logger.info("Done Adding Monsters");

        logger.info("Adding Card Groups");

        MusicalGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : CardLibrary.getAllCards())
        {
            if (card.hasTag(CustomTags.MUSICAL)) {
                MusicalGroup.addToBottom(card);
            }
        }

        logger.info("Done Adding Card Groups");
    }

    @Override
    public void receiveEditCharacters() {

        logger.info("Beginning to edit characters. " + "Add " + TheMusician.Enums.COLOR_GOLD.toString());

        BaseMod.addCharacter(new TheMusician("The Musician", TheMusician.Enums.THE_MUSICIAN),
                THE_MUSICIAN_BUTTON, THE_MUSICIAN_PORTRAIT, TheMusician.Enums.THE_MUSICIAN);

        logger.info("Added " + TheMusician.Enums.THE_MUSICIAN.toString());
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new MusicalInstrument(), TheMusician.Enums.COLOR_GOLD);
        BaseMod.addRelicToCustomPool(new MusicalAward(), TheMusician.Enums.COLOR_GOLD);
        BaseMod.addRelicToCustomPool(new MusicalCrowd(), TheMusician.Enums.COLOR_GOLD);
        BaseMod.addRelicToCustomPool(new MusicalDestiny(), TheMusician.Enums.COLOR_GOLD);

        // Mark relics as seen (the others are all starters so they're marked as seen in the character file
        UnlockTracker.markRelicAsSeen(MusicalInstrument.ID);
        UnlockTracker.markRelicAsSeen(MusicalAward.ID);
        UnlockTracker.markRelicAsSeen(MusicalCrowd.ID);
        UnlockTracker.markRelicAsSeen(MusicalDestiny.ID);
        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding Musician variables");
        //Ignore this
        pathCheck();

        // Add the Custom Dynamic variables
        BaseMod.addDynamicVariable(new MusicianCustomVariable());
        BaseMod.addDynamicVariable(new MusicianSecondMagicNumber());
        logger.info("Done Musician adding Variables.");

        //====== CARDS IN ORDER ======//
        //====== CARDS ADDED: 7 ======//
        logger.info("Adding Musician Cards.");
        BaseMod.addCard(new Defend_Musician());
        BaseMod.addCard(new MusicalBrass());
        BaseMod.addCard(new MusicalPercussion());
        BaseMod.addCard(new MusicalString());
        BaseMod.addCard(new MusicalWoodWind());
        BaseMod.addCard(new Strike_Musician());
        BaseMod.addCard(new TheBand());


        //====== UNLOCK CARD IN ORDER ======//
        //====== UNLOCKED CARDS: 7 ======//
        UnlockTracker.unlockCard(Defend_Musician.CARD_ID);
        UnlockTracker.unlockCard(MusicalBrass.CARD_ID);
        UnlockTracker.unlockCard(MusicalPercussion.CARD_ID);
        UnlockTracker.unlockCard(MusicalString.CARD_ID);
        UnlockTracker.unlockCard(MusicalWoodWind.CARD_ID);
        UnlockTracker.unlockCard(Strike_Musician.CARD_ID);
        UnlockTracker.unlockCard(TheBand.CARD_ID);


        //====== CARD TESTING ======//
        BaseMod.addCard(new TestDefend());
        BaseMod.addCard(new TestingDefend());
        BaseMod.addCard(new TestingDefender());
        BaseMod.addCard(new TestStrike());
        BaseMod.addCard(new TestingStrike());
        BaseMod.addCard(new TestingStikers());

        logger.info("Done adding Musician Cards.");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/strings/TheMusician-card-strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/strings/TheMusician-relic-strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/strings/TheMusician-character-strings.json");

        //PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/strings/TheMusician-powers-strings.json");

        //MonsterStrings
        BaseMod.loadCustomStringsFile(MonsterStrings.class,
                getModID() + "Resources/strings/TheMusician-monsters-strings.json");
    }

    private void InitializeMonsters() {
        BaseMod.addMonster("TheMusician:MusicalLover", () -> new MonsterGroup(new AbstractMonster[] {new MusicalLover(0, 0)}));
    }
}
