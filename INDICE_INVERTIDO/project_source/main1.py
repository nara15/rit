
from save_file.save_file import load_obj

from search import process_query, search_1


dictionary = load_obj("dict")
posting = load_obj("posting")
norms = load_obj("norms")
documento_t = load_obj("docs")


query = [("Persea americana",1),("Sacoglottis holdridgei",1)]

#r = process_query(query, inverted)

sim,ranking = search_1(dictionary, posting, query, list(documento_t.keys()),norms)


for i in sorted(ranking, key=lambda x: x[1], reverse=True):

    print(i)
    
