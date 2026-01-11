BUILD_DIR := build
GEN_JSONS := src/lib/data

GEN_POEMS := $(GEN_JSONS)/poems.json
GEN_POEM_INDEXES := $(GEN_JSONS)/selected_poems_indexes.json
GEN_ROMAN := $(GEN_JSONS)/roman_num_poems.json

SCRIPT_DIR := scripts

POEMS_SCRIPT := $(SCRIPT_DIR)/getJsonPoems.py
ROMAN_SCRIPT := $(SCRIPT_DIR)/getNumerals.py
SHARED_SCRIPT := $(SCRIPT_DIR)/shared.py

all: $(BUILD_DIR)

$(BUILD_DIR): $(shell find src/ -type f) $(shell find static/ -type f) \
svelte.config.js vite.config.js node_modules/ \
$(GEN_POEMS) $(GEN_ROMAN) $(GEN_POEM_INDEXES)
	npm run build

node_modules/: package-lock.json
	npm ci

package-lock.json: package.json
	npm install

$(GEN_POEMS) $(GEN_POEM_INDEXES): $(POEMS_SCRIPT) $(SHARED_SCRIPT)
	rm -f $(GEN_POEMS)
	rm -f $(GEN_JSONS)/selected_poems_indexes.json
	python $(POEMS_SCRIPT)

$(GEN_ROMAN): $(ROMAN_SCRIPT) $(SHARED_SCRIPT)
	rm -f $(GEN_ROMAN)
	python $(ROMAN_SCRIPT)