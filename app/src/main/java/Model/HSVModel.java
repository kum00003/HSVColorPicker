package Model;

import java.util.Observable;

import android.graphics.Color;

/**
 * The model holds the data.
 *
 * @author Sercan Kum kum00003@algonquinlive.com
 * @version 1.0
 */

public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final float MAX_HUE = 360f;
    public static final float MAX_PER   = 100f;
    public static final float MIN_HUE = 0f;
    public static final float MIN_PER   = 0f;

    // INSTANCE VARIABLES
    private float hue;
    private float saturation;
    private float value;


    /**
     * No argument constructor.
     *
     * Instantiate a new instance of this class, and
     * initialize red, green, blue, and alpha to max values.
     */
    public HSVModel() {
        this( MAX_HUE, MAX_PER, MAX_PER );
    }

    /**
     * Convenience constructor.
     *
     */
    public HSVModel( float hue, float saturation, float value ) {
        super();

        this.hue = hue;
        this.saturation  = saturation;
        this.value = value;
    }

    public void asBlack()   { this.setHSV( MIN_HUE, MIN_PER,MIN_PER ); }
    public void asRed()     { this.setHSV( MIN_HUE, MAX_PER,MAX_PER ); }
    public void asLime()    { this.setHSV( 120f, MAX_PER,MAX_PER);     }
    public void asBlue()    { this.setHSV( 240f, MAX_PER,MAX_PER);     }
    public void asYellow()  { this.setHSV( 60f, MAX_PER,MAX_PER);      }
    public void asCyan()    { this.setHSV( 180f, MAX_PER,MAX_PER);     }
    public void asMagenta() { this.setHSV( 300f, MAX_PER,MAX_PER);     }
    public void asSilver()  { this.setHSV( MIN_HUE, MIN_PER,75f);      }
    public void asGray()    { this.setHSV( MIN_HUE, MIN_PER,50f);      }
    public void asMaroon()  { this.setHSV(MIN_HUE, MAX_PER,50f);       }
    public void asOlive()   { this.setHSV( 60f, MIN_PER,50f);          }
    public void asGreen()   { this.setHSV( 120f, MAX_PER,50f );        }
    public void asPurple()  { this.setHSV( 300f, MAX_PER,50f );        }
    public void asTeal()    { this.setHSV( 180f, MAX_PER,50f );        }
    public void asNavy()      { this.setHSV( 240f, MAX_PER,50f );        }



    // GETTERS
    public float getHue() { return hue; }
    public float getSaturation()  { return saturation; }
    public float getValue() { return value;}
    public int   getColor() {
        return Color.HSVToColor(new float[] { getHue(), getSaturation()/100, getValue()/100});
   }
    // SETTERS
    public void setHue( float hue ) {
        this.hue = hue;
        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
        this.updateObservers();
    }

    public void setValue(float value) {
        this.value = value;
        this.updateObservers();
    }

    // Convenient setter: set H, S, V
    public void setHSV( float hue, float saturation, float value) {
        this.hue   = hue;
        this.saturation = saturation;
        this.value  = value;
        this.updateObservers();
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "HSV[h=" + hue + " s=" + saturation + " v=" + value +"]";
    }

    // Proof that my model is independent of any view.
    public static void main( String[] args ) {
        HSVModel model = new HSVModel();

        System.out.println( model);
    }
}