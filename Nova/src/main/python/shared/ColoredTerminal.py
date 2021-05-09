import os
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
