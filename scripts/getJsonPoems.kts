// Delete this file when the python file will be certain to work
val poemsFile: String =
        "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
    val selectedPoemIndexes: IntArray = intArrayOf(1, 2, 3, 4, 5).map { it - 1 }.toIntArray()

    File("../src/lib/data/selected_poems.json").run {
        parentFile.mkdirs()
        writeText(
            run {
                val poems: List<List<String>> = URL(poemsFile).readText()
                    .split("///")
                    .map {
                        it.trim().split("\n\n")
                    }

                Json.encodeToString(
                    poems.filterIndexed { i, _ ->
                        selectedPoemIndexes.contains(i)
                    }
                )
            }
        )
    }