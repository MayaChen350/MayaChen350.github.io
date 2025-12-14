BUILD_DIR := build
GEN_JSONS := src/lib/data

GEN_POEMS := $(GEN_JSONS)/selected_poems.json
GEN_ROMAN := $(GEN_JSONS)/roman_num_poems.json

SCRIPT_DIR := scripts

POEMS_SCRIPT := $(SCRIPT_DIR)/getJsonPoems.py
ROMAN_SCRIPT := $(SCRIPT_DIR)/getNumerals.py
SHARED_SCRIPT := $(SCRIPT_DIR)/shared.py

all: $(BUILD_DIR) node_modules

node_modules: package-lock.json
	npm ci

package-lock.json: package.json
	npm install

$(BUILD_DIR): $(shell find src/ -type f) $(shell find static/ -type f) svelte.config.js vite.config.js $(GEN_POEMS) $(GEN_ROMAN)
	npm run build

$(GEN_POEMS): $(POEMS_SCRIPT) $(SHARED_SCRIPT)
	rm -f $(GEN_POEMS)
	python $(POEMS_SCRIPT)

$(GEN_ROMAN): $(ROMAN_SCRIPT) $(SHARED_SCRIPT)
	rm -f $(GEN_ROMAN)
	python $(ROMAN_SCRIPT)