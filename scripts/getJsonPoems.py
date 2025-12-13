# Source: my gradle build file on my gradle file
# https://github.com/MayaChen350/MayaChen350.github.io/blob/5eefe11b9758d93869e1b652c8a318f7caca5116/site/build.gradle.kts
import requests
import json

poemsFileLink = "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
poemsFilePath = "src/lib/data/selected_poems"
selectedPoemNumbers = [1, 2, 3, 4, 5]

poems: list[list[str]] = list(map(lambda it: it.strip().split("\n\n"),
                 requests.get(poemsFileLink).text.split("///")))

selectedPoemIndexes = map(lambda x: x - 1, selectedPoemNumbers)
selectedPoems: list[list[str]] = []
if selectedPoemIndexes:
    for i, poem in enumerate(poems):
        if i in selectedPoemIndexes:
            selectedPoems.append(poem)

with open(poemsFilePath + ".json", "x") as f:
    json.dump(selectedPoems, f)
