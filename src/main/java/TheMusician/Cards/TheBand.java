package TheMusician.Cards;

import TheMusician.Characters.TheMusician;
import TheMusician.MusicianMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheMusician.MusicianMod.MusicalGroup;

public class TheBand extends CustomCard {

    //~~~~~~~~~~~~~~~~~~ Getting All the Card's Information ~~~~~~~~~~~~~~~~~~//
    public static final String CARD_ID = MusicianMod.makeID("TheBand");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = MusicianMod.makeCardPath("skills/Beta.png");
    public static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //~~~~~~~~~~~~~~~~~~ Getting The Card Aspects ~~~~~~~~~~~~~~~~~~//
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheMusician.Enums.COLOR_GOLD;


    //~~~~~~~~~~~~~~~~~~ Getting the Cards Numbers ~~~~~~~~~~~~~~~~~~//
    private static final int COST = 1;
    private static final int UPGRADE_MINUS_COST = 0;


    //~~~~~~~~~~~~~~~~~~ Initializing the Card ~~~~~~~~~~~~~~~~~~//
    public TheBand() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    //~~~~~~~~~~~~~~~~~~ Uses of the Card ~~~~~~~~~~~~~~~~~~//
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard tempCard = MusicalGroup.getRandomCard(AbstractDungeon.cardRandomRng);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(tempCard));
    }

    //~~~~~~~~~~~~~~~~~~ Upgraded Card ~~~~~~~~~~~~~~~~~~
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_MINUS_COST);
            initializeDescription();
        }
    }
}
