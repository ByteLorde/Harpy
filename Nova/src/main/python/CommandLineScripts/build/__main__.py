import sys
import os
from pathlib import Path
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

def writeExecutable(pathToFile):
    ColoredTerminal.print("Target:", end=" ")
    ColoredTerminal.printYellow("[" + pathToFile + "]", end="")
    ColoredTerminal.print("...")
    return os.system('python -m PyInstaller ' + pathToFile + ' --onefile')

def getPythonBinPath():
    return str(Path(os.path.dirname(sys.executable)))

def copyToPythonBin(src, targetName):
    src = 'dist/' + src
    ColoredTerminal.print("Moving binaries to python root:", end=" ")
    ColoredTerminal.printYellow("[" + getPythonBinPath() + "]")

    target = getPythonBinPath() + '\\' + targetName
    command = 'cp {0} {1}'.format(src, target)

    ColoredTerminal.print("Copying", end=" ")
    ColoredTerminal.printYellow('[' + src + ']', end=' ')
    ColoredTerminal.print('->', end=' ')
    ColoredTerminal.printGreen(getPythonBinPath(), end=' ')
    ColoredTerminal.print('as', end=' ')
    ColoredTerminal.printBlue(targetName)

    return os.system(command)

def getExecutableName():
    for arg in sys.argv:
        if arg.startswith('--name='):
            return arg.replace('--name=', '').replace('"', '').replace('"', '').replace('.exe', '') + '.exe'

def clean():
    ColoredTerminal.printYellow("Cleaning up...")

    cleanupFiles = [
        '*.spec',
        'build/',
        'dist/'
    ]

    for file in cleanupFiles:
        ColoredTerminal.print("Removing", end=" ")
        ColoredTerminal.printYellow('[' + file + ']')
        os.system('rm -rf ' + file)


if __name__ == '__main__':
    args = sys.argv

    target = None if len(args) <= 1 else args[1]
    additionalArgs = [] if len(args) <= 2 else args[2::]

    if not target:
        ColoredTerminal.printRed('[ERROR]', end=' ')
        ColoredTerminal.printYellow('- No Target Provided.')
        ColoredTerminal.print('Command Syntax is: nova build', end=' ')
        ColoredTerminal.printYellow('<target> <additional_args?>')
        ColoredTerminal.print('Please observe the above and try again.')
    else:
        currentPath = os.getcwd()
        if not target.endswith('.py'):
            target = target + '.py'
        targetPath = currentPath + '\\' + target

        print('the path:', targetPath)
        if not os.path.isfile(targetPath):
            ColoredTerminal.printRed('[ERROR]', end=' ')
            ColoredTerminal.printYellow("File not found")
            ColoredTerminal.print('Target does not exist:', end=' ')
            ColoredTerminal.printYellow(targetPath)
        else:
            name = getExecutableName() or target.replace('.py', '.exe')
            print('the name:', name)
            writeFailure = writeExecutable(targetPath)
            if (writeFailure):
                ColoredTerminal.printRed('[ERROR]', end=' ')
                ColoredTerminal.printYellow("- Couldn't write binaries for " + targetPath)
                exit('0')
            copyToPythonBin(os.listdir('dist/')[0], name)
            if len(str(name).replace('.exe', '')) > 6 or str(name) == 'build.exe':
                shortcutName = name[0:3] + '.exe'
                ColoredTerminal.printYellow('\tCreating shortcut command:', end=' ')
                ColoredTerminal.printBlue('\t' + shortcutName)
                copyToPythonBin(os.listdir('dist/')[0], shortcutName)  # Shortcut command
            clean()
            ColoredTerminal.printGreen('Build complete!')
            ColoredTerminal.print("Your script is now installed as", end=' ')
            ColoredTerminal.printBlue(name.replace('.exe', ''), end='')
            ColoredTerminal.print('.')

