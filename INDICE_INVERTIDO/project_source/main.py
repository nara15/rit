


from inverted_index import term_to_docs, create_inverted_index

from process_xml_files import parseXML_Files



documents, words = parseXML_Files()

inverted = term_to_docs(documents, words)


dictionary, posting = create_inverted_index(documents, inverted)
