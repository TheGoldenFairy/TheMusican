package TheMusician.Cards;

import TheMusician.Actions.CornetAction;
import TheMusician.Characters.TheMusician;
import TheMusician.MusicianMod;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheMusician.Patches.CustomTags.BRASS;

public class Cornet extends CustomCard {
    //~~~~~~~~~~~~~~~~~~ Getting All the Card's Information ~~~~~~~~~~~~~~~~~~//
    public static final String CARD_ID = MusicianMod.makeID("Cornet");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = MusicianMod.makeCardPath("attacks/Beta.png");
    public static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //~~~~~~~~~~~~~~~~~~ Getting The Card Aspects ~~~~~~~~~~~~~~~~~~//
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TheMusician.Enums.COLOR_GOLD;


    //~~~~~~~~~~~~~~~~~~ Getting the Cards Numbers ~~~~~~~~~~~~~~~~~~//
    private static final int COST = 3;
    private static final int DAMAGE = 30;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final int DRAW_AMT = 1;


    //~~~~~~~~~~~~~~~~~~ Initializing the Card ~~~~~~~~~~~~~~~~~~//
    public Cornet() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DRAW_AMT;
        tags.add(BRASS);
    }

    //~~~~~~~~~~~~~~~~~~ Uses of the Card ~~~~~~~~~~~~~~~~~~//
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
        }
        AbstractDungeon.actionManager.addToBottom(new CornetAction(p));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
    }

    //~~~~~~~~~~~~~~~~~~ Upgraded Card ~~~~~~~~~~~~~~~~~~
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
