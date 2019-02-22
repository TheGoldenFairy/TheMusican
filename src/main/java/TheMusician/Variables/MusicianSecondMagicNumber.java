package TheMusician.Variables;

import TheMusician.Cards.AbstractMusicianCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static TheMusician.MusicianMod.makeID;


//Class Made and Taken From the Default. Creator Grem.
public class MusicianSecondMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("SecondMagic");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractMusicianCard) card).isMusicianSecondMagicNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractMusicianCard) card).MusicianSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractMusicianCard) card).MusicianBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractMusicianCard) card).upgradedMusicianSecondMagicNumber;
    }
}
