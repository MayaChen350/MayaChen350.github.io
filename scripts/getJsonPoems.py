# Source: my gradle build file on my gradle file
# https://github.com/MayaChen350/MayaChen350.github.io/blob/5eefe11b9758d93869e1b652c8a318f7caca5116/site/build.gradle.kts
import json

from shared import selectedPoemNumbers, poems, poemsFilePath

selectedPoems: list[list[str]] = []
if selectedPoemNumbers:
    selectedPoemIndexes = list(map(lambda x: x - 1, selectedPoemNumbers))
    for i, poem in enumerate(poems):
        if i in selectedPoemIndexes:
            selectedPoems.append(poem)
else:
    selectedPoems = poems

with open(poemsFilePath, "x") as f:
    json.dump(selectedPoems, f)
