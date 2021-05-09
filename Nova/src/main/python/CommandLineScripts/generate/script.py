import os
import sys
import time

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

if (not args or len(args) == 0):
    ColoredTerminal.printRed('[ERROR]', end=' ')
    ColoredTerminal.printYellow('- No Target Provided.')
    ColoredTerminal.print('Command Syntax is: nova gen script', end=' ')
    ColoredTerminal.printYellow('<target> <additional_args?>')
    ColoredTerminal.print('Command Syntax is: nova generate script', end=' ')
    ColoredTerminal.printYellow('<target> <additional_args?>')
    ColoredTerminal.print('Please observe the above and try again.')
    exit('1')

scriptName = args[0]
ColoredTerminal.print('Generating:', end=' ')
ColoredTerminal.printBlue('[' + scriptName + ']', end=' ')
ColoredTerminal.print('...')

currentPath = os.getcwd()
scriptPath = '{0}\\{1}'.format(currentPath, scriptName)
ColoredTerminal.print('Path:', end=' ')
ColoredTerminal.printYellow('[' + scriptPath + ']')
os.makedirs(scriptPath)

javaPath = scriptPath + '\\' + scriptName + '.java'
ColoredTerminal.print('Writing Java template to:', end=' ')
ColoredTerminal.printYellow('[' + javaPath + ']')

f = open(javaPath, "a")

scriptTemplate = """
package scripts.{REPLACE_ME};

import nova.NovaConstants;
import nova.NovaScript;

public class {REPLACE_ME} extends NovaScript {

    public {REPLACE_ME}() {
        super({REPLACE_ME}.class.getName());
    }

    @Override
    public int tick() {
        return NovaConstants.DEFAULT_DELAY;
    }
    
    @Override
    public boolean isComplete() {
        return false;
    }
}
""".replace('{REPLACE_ME}', scriptName)

f.write(scriptTemplate)
f.close()

ColoredTerminal.printGreen('Successfully created script:', end=' ')
ColoredTerminal.printBlue(scriptName)
