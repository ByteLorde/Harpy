import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
    
@ScriptManifest(name = "Wintertodt", 
                description = "A Bot titled Wintertodt", 
                category = Category.MISC,
                author = "Anonymous", 
                version = 1.01, 
                image = "")           
public class Wintertodt extends SingleStateNovaBot {

    public Wintertodt() {
        super(Wintertodt.class.getName());

        // Scripts for the bot to load. Ordered.
        String[] scripts = new String[] {
            
        };

        this.loadScripts(scripts);
        this.enable();
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
