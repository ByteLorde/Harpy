import os
import time
import sys


class BotFileContents:

    POM_FILE = """<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>{{REPLACE_ME}}</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
"""

    MULTI_STATE_JAVA_FILE = """import nova.MultiStateNovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "{{REPLACE_ME}}", 
                description = "A Bot titled {{REPLACE_ME}}", 
                author = "Anonymous", 
                version = 1.01, 
                category = Category.MISC, 
                image = "")
public class {{REPLACE_ME}} extends MultiStateNovaBot {

    public {{REPLACE_ME}}() {
        super({{REPLACE_ME}}.class.getName());

        // Scripts for the bot to load. Unordered.
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
"""

    SINGE_STATE_JAVA_FILE = """import nova.SingleStateNovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
    
@ScriptManifest(name = "{{REPLACE_ME}}", 
                description = "A Bot titled {{REPLACE_ME}}", 
                category = Category.MISC,
                author = "Anonymous", 
                version = 1.01, 
                image = "")           
public class {{REPLACE_ME}} extends SingleStateNovaBot {

    public {{REPLACE_ME}}() {
        super({{REPLACE_ME}}.class.getName());

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
"""

    IML_FILE = """<?xml version="1.0" encoding="UTF-8"?>
<module org.jetbrains.idea.maven.project.MavenProjectsManager.isMavenModule="true" type="JAVA_MODULE" version="4">
  <component name="NewModuleRootManager" LANGUAGE_LEVEL="JDK_1_8">
    <output url="file://$MODULE_DIR$/target/classes" />
    <output-test url="file://$MODULE_DIR$/target/test-classes" />
    <content url="file://$MODULE_DIR$">
      <sourceFolder url="file://$MODULE_DIR$/src/main/java" isTestSource="false" />
      <sourceFolder url="file://$MODULE_DIR$/src/main/resources" type="java-resource" />
      <sourceFolder url="file://$MODULE_DIR$/src/test/java" isTestSource="true" />
      <excludeFolder url="file://$MODULE_DIR$/target" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />
    <orderEntry type="library" name="client" level="project" />
    <orderEntry type="library" name="Nova" level="project" />
    <orderEntry type="library" name="Python 3.9 interpreter library" level="application" />
  </component>
</module>"""

    XML_FILE = """\n\t\t\t<module fileurl="file://$PROJECT_DIR$/{{REPLACE_ME}}/{{REPLACE_ME}}.iml" filepath="$PROJECT_DIR$/{{REPLACE_ME}}/{{REPLACE_ME}}.iml" />"""

class ColoredTerminal:

    @staticmethod
    def printRed(text, end='\n'):
        ColoredTerminal.printColored(text, '31', end=end)

    @staticmethod
    def printPink(text, end='\n'):
        ColoredTerminal.printColored(text, '35', end=end)

    @staticmethod
    def printBlue(text, end='\n'):
        ColoredTerminal.printColored(text, '34', end=end)

    @staticmethod
    def printYellow(text, end='\n'):
        ColoredTerminal.printColored(text, '33', end=end)

    @staticmethod
    def printGreen(text, end='\n'):
        ColoredTerminal.printColored(text, '32', end=end)

    @staticmethod
    def printColored(text, color, end):
        print(f"\033[1;" + color + ";40m " + text, end='')
        os.system('')
        print(f"\033[1;37;40m ", end=end)
        os.system('')
        time.sleep(.1)

    @staticmethod
    def print(text, end='\n'):
        print(text, end=end)
        time.sleep(.4)

args = sys.argv[1::]

if not args or len(args) == 0:
    ColoredTerminal.printRed('[ERROR]', end=' ')
    ColoredTerminal.printYellow('- No Target Provided.')
    ColoredTerminal.print('Command Syntax is: nova generate bot', end=' ')
    ColoredTerminal.printYellow('<target> <additional_args?>')
    ColoredTerminal.print('Command Syntax is: nova gen bot', end=' ')
    ColoredTerminal.printYellow('<target> <additional_args?>')
    ColoredTerminal.print('Please observe the above and try again.')
else:
    for arg in args:
        arg = arg.lower()
        if arg == '--multistate':
            ColoredTerminal.printColored('MultiState Configuration Detected.')

    scriptName = args[0]
    ColoredTerminal.print('Generating:', end=' ')
    ColoredTerminal.printBlue('[' + scriptName + ']', end=' ')
    ColoredTerminal.print('...')

    currentPath = os.getcwd()

    directoriesToCreate = [
        '{0}\\{1}\\src\\main\\java\\constants'.format(currentPath, scriptName),
        '{0}\\{1}\\src\\main\\java\\scripts'.format(currentPath, scriptName),
        '{0}\\{1}\\src\\main\\resources'.format(currentPath, scriptName)
    ]

    for directory in directoriesToCreate:
        ColoredTerminal.print('Path:', end=' ')
        ColoredTerminal.printYellow('[' + directory + ']')
        os.makedirs(directory)

    imlTarget = scriptName + '/' + scriptName + '.iml'
    ColoredTerminal.print('Writing IML File to:', end=' ')
    ColoredTerminal.printYellow('[' + imlTarget + ']'
                                )
    imlHandle = open(imlTarget, 'w')
    imlHandle.write(BotFileContents.IML_FILE.replace('{{REPLACE_ME}}', scriptName))
    imlHandle.close()

    pomTarget = '{0}/pom.xml'.format(scriptName)
    ColoredTerminal.print('Writing POM File to:', end=' ')
    ColoredTerminal.printYellow('[' + pomTarget + ']')
    pomHandle = open(pomTarget, 'w')
    pomHandle.write(BotFileContents.POM_FILE.replace('{{REPLACE_ME}}', scriptName))
    pomHandle.close()

    botContents = BotFileContents.SINGE_STATE_JAVA_FILE

    for arg in args:
        arg = arg.lower()
        if arg == '--multistate':
            botContents = BotFileContents.MULTI_STATE_JAVA_FILE
            ColoredTerminal.printBlue('MultiState Configuration set.')
            break

    javaOutputPath = '{0}\\{1}\\src\\main\\java\\{1}.java'.format(currentPath, scriptName)
    ColoredTerminal.print('Writing Java Template to', end=' ')
    ColoredTerminal.printYellow('[' + javaOutputPath + ']')
    javaHandle = open(javaOutputPath, 'w')
    javaHandle.write(botContents.replace('{{REPLACE_ME}}', scriptName))
    javaHandle.close()

    ColoredTerminal.printYellow('Adding to .idea modules...')
    modulesXML = open('..\\.idea\\modules.xml', 'r')
    content = modulesXML.read()
    modulesXML.close()

    content = content.replace('<modules>', '<modules>' + BotFileContents.XML_FILE.replace('{{REPLACE_ME}}', scriptName))
    modulesXML = open('..\\.idea\\modules.xml', 'w')

    modulesXML.write(content)
    modulesXML.close()

    ColoredTerminal.printGreen('Successfully created bot:', end=' ')
    ColoredTerminal.printBlue(scriptName)


