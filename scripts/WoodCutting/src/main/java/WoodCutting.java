import nova.SingleStateNovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
    
@ScriptManifest(name = "WoodCutting", 
                description = "A Bot titled WoodCutting", 
                category = Category.MISC,
                author = "Anonymous", 
                version = 1.01, 
                image = "")           
public class WoodCutting extends SingleStateNovaBot {

    public WoodCutting() {
        super(WoodCutting.class.getName());

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
