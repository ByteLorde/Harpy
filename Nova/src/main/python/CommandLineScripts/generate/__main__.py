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


def isValidTarget(target):
    return not not target and target in getValidTargets()

def getValidTargets():
    return ['bot', 'script']

def printValidTargetMessage():
    ColoredTerminal.print('Valid ', end='')
    ColoredTerminal.printYellow('targets ', end='')
    ColoredTerminal.print('are:')
    for name in getValidTargets():
        if name.startswith('__'):
            continue
        ColoredTerminal.printGreen('\t-> ' + name)
        time.sleep(.05)

if __name__ == '__main__':
    args = sys.argv

    target = None if len(args) <= 1 else args[1]
    additionalArgs = [] if len(args) <= 2 else args[2::]

    if not isValidTarget(target):
        ColoredTerminal.printRed('[ERROR]', end='')
        ColoredTerminal.printYellow(' - No target provided.')
        ColoredTerminal.print()
        printValidTargetMessage()
        ColoredTerminal.print()
        ColoredTerminal.print('Command Syntax is: nova build ', end='')
        ColoredTerminal.printYellow('<target> <additional_args?>')
        ColoredTerminal.print('Please observe the above and try again.')
        exit()

    command = target + ' ' + ' '.join(additionalArgs)
    os.system(command)
