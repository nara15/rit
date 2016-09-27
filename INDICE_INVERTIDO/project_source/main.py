


from inverted_index import term_to_docs, create_inverted_index

#from process_xml_files import parseXML_Files

from process_txt_files import parseTexts

from search import process_query, search


documents, words = parseTexts('G:/git/rit/INDICE_INVERTIDO/tests/*txt')

inverted = term_to_docs(documents, words)


dictionary, posting = create_inverted_index(documents, inverted)





query = [("dinosaurios",2),("tercios",2)]


r = process_query(query, inverted)


ranking = search(dictionary, posting, r, list(documents.keys()))
