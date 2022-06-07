# vim:fdm=marker

# Constants                                                                 {{{1
# ==============================================================================

COLOR_RED=\033[0;31m
COLOR_GREEN=\033[0;32m
COLOR_YELLOW=\033[0;33m
COLOR_BLUE=\033[0;34m
COLOR_NONE=\033[0m
COLOR_CLEAR_LINE=\r\033[K

.ONESHELL:
SHELL = bash
.SHELLFLAGS = -ec


# Targets                                                                   {{{1
# ==============================================================================


# Help                              {{{2
# ======================================

help:
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "$(COLOR_BLUE)%-18s$(COLOR_NONE) %s\n", $$1, $$2}'

.PHONY: clean
clean: ## Remove all artefacts
	@echo 'Cleaning application'
	@gradle clean
	@rm -f ./${APP_JAR}

.PHONY: create-book
create-book: ## Call the API and create a task with a random ID
	@curl -s -X POST 'http://localhost:8080/books' \
		--header 'Content-Type: application/json' \
		-d '{ "title": "book-1", "publishYear": 2000 }' \
		| jq -r '.'

list-books: ## Call the API and list all the current tasks
	@curl -s -X GET 'http://localhost:8080/books' \
		--header 'Content-Type: application/json' \
		| jq -r '.'

