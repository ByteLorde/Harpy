import nova.SingleStateNovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
    
@ScriptManifest(name = "WoodcuttingBot", 
                description = "A Bot titled WoodcuttingBot", 
                category = Category.MISC,
                author = "Anonymous", 
                version = 1.01, 
                image = "")           
public class WoodcuttingBot extends SingleStateNovaBot {

    public WoodcuttingBot() {
        super(WoodcuttingBot.class.getName());

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
