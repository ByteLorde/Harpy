import os
from pathlib import Path
import PyInstaller.__main__


def convertToExecutables():
    scriptsRootPath = str(Path(os.path.dirname(__file__)).parent) + '\\'

    scriptPaths = []

    for file in os.listdir(scriptsRootPath):
        if not os.path.isfile(file):
            if file == '__pycache__':
                continue

            # print("Checking script folder:", file)
            scriptFolder = scriptsRootPath + file + '\\'

            for scriptFile in os.listdir(scriptFolder):
                scriptPath = scriptFolder + scriptFile
                try:

                    print("Installing", scriptPath)
                    # os.system('python -m PyInstaller ' + scriptPath)
                except:
                    print('oh no', file)

    for script in scriptPaths:
        print("Adding Script: " + script)


if __name__ == '__main__':
    convertToExecutables()
