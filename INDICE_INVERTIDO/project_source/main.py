


from inverted_index import term_to_docs, create_inverted_index

#from process_xml_files import parseXML_Files

from process_txt_files import parseTexts

from search import process_query, search


documents, words, documento_t = parseTexts('G:/git/rit/INDICE_INVERTIDO/tests/*txt')

inverted = term_to_docs(documents, words)


dictionary, posting = create_inverted_index(documents, inverted)





query = [("fenómeno",2)]

r = process_query(query, inverted) # Cálculo de los pesos de la consulta


sim,ranking = search(dictionary, posting, r, list(documento_t.keys()))
