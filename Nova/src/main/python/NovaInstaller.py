import os
from pathlib import Path
import sys

from shared.ColoredTerminal import ColoredTerminal


def getPythonBinPath():
    return str(Path(os.path.dirname(sys.executable)))

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

def complete():

    ColoredTerminal.printGreen("Installation complete!")
    print()
    ColoredTerminal.printYellow("You can now interface with", end=" ")
    ColoredTerminal.printRed("Nova CLI!", end=" ")
    ColoredTerminal.printYellow("via the Command Line!")
    ColoredTerminal.printBlue("Example Commands:", end='\n\t')
    print("- nova build", end=" ")
    ColoredTerminal.printYellow("<target>", end="\t\t\t<--- Generate a Nova Bot\n\t")
    print("- nova generate script", end=" ")
    ColoredTerminal.printYellow("<target>", end="\t<--- Generate a Nova Script\n\t")
    print("- nova gen script", end=" ")
    ColoredTerminal.printYellow("<target>", end="\t\t<--- Generate a Nova Script using shortcode command\n\t\t")
    exit()

def copyToPythonRoot():
    ColoredTerminal.print("Moving binaries to python root:", end=" ")
    ColoredTerminal.printYellow("[" + getPythonBinPath() + "]")

    binaryPath = 'dist'
    for file in os.listdir(binaryPath):

        if (str(file) == '__main__.exe' or str(file) == 'NovaCommandLine.exe'):
            continue

        pathToFile = binaryPath + '\\' + str(file)
        ColoredTerminal.print("Copying", end=" ")
        ColoredTerminal.printYellow('[' + pathToFile + ']', end=' ')
        ColoredTerminal.print('->', end=' ')
        ColoredTerminal.printGreen(getPythonBinPath(), end=' ')
        ColoredTerminal.print('as', end=' ')
        ColoredTerminal.printBlue(file)
        os.system('cp ' + pathToFile + ' ' + getPythonBinPath())
        if (len(str(file).replace('.exe', '')) > 6):
            shortcutName = str(file)[0:3] + '.exe'
            ColoredTerminal.printYellow('\tCreating shortcut command:', end=' ')
            ColoredTerminal.printBlue('\t-> ' + shortcutName)
            os.system('cp ' + pathToFile + ' ' + getPythonBinPath() + '\\' + shortcutName) # Shortcut command

def intro():
    ColoredTerminal.printGreen('Installing', end=' ')
    ColoredTerminal.printRed('Nova CLI', end='')
    ColoredTerminal.print('...')

def writeExecutable(pathToFile):
    ColoredTerminal.print("Target:", end=" ")
    ColoredTerminal.printYellow("[" + pathToFile + "]", end="")
    ColoredTerminal.print("...")
    os.system('python -m PyInstaller ' + pathToFile + ' --onefile')

def writeExecutables():
    ColoredTerminal.printYellow("Creating Nova binaries...")

    contextRoot = str(Path(os.path.dirname(__file__)))
    pathToScripts = contextRoot + '\\CommandLineScripts'
    pathToNovaFile = pathToScripts + '\\NovaCommandLine.py'

    writeExecutable(pathToNovaFile)
    os.system('cp dist\\NovaCommandLine.exe dist\\nova.exe')


    for item in os.listdir(pathToScripts):
        newPath = pathToScripts + '\\' + item

        if os.path.isdir(newPath):
            for file in os.listdir(newPath):
                if str(file) == '__pycache__':
                    continue

                pathToFile = newPath + '\\' + file
                writeExecutable(pathToFile)

                if str(file) == '__main__.py':
                    os.system('cp dist\\' + str(file).replace('.py', '.exe') + ' dist\\' + item + '.exe')


if __name__ == '__main__':
    intro()
    writeExecutables()
    copyToPythonRoot()
    clean()
    complete()




