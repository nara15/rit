


from inverted_index import term_to_docs, create_inverted_index

from process_txt_files import parseTexts



documents, words = parseTexts()

inverted = term_to_docs(documents, words)


dictionary, posting = create_inverted_index(documents, inverted)
