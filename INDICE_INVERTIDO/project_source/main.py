
from inverted_index import term_to_docs, create_inverted_index

from process_xml_files import parseXML_Files

from save_file.save_file import save_to_file

documento_t,documents, words = parseXML_Files('C:/git/TP1-2016ii/Flora-20160122/*xml')
inverted = term_to_docs(documents, words)
dictionary, posting,norms = create_inverted_index(documents, inverted)

save_to_file(dictionary, "dict")
save_to_file(posting, "posting")
save_to_file(norms, "norms")
save_to_file(documento_t, "docs")


