


from inverted_index import term_to_docs, create_inverted_index

from process_xml_files import parseXML_Files

from process_txt_files import parseTexts


documents, words = parseTexts('M:/rit/INDICE_INVERTIDO/tests/*txt')

inverted = term_to_docs(documents, words)


dictionary, posting = create_inverted_index(documents, inverted)
