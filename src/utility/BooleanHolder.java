package utility;

/**
 *
 * @author harry bournis
 */
public class BooleanHolder {
    
    private boolean b;
    
    public BooleanHolder() {
    }
    
    public BooleanHolder(boolean b) {
        this.b = b;
    }
    
    public void set(boolean newValue) {
        b = newValue;
    }
    
    public boolean get() {
        return b;
    }
    
}
