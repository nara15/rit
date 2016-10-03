import re

from inverted_index import term_to_docs, create_inverted_index
from process_xml_files import parseXML_Files

from save_file.save_file import save_to_file, load_obj
from save_query import save_to_html

from search import process_prefix_query, search
from search_structure import structuredQueries


def scan_query(input_q):
    
   query_terms = re.findall(r'\-{0,1}\+{0,1}[À-ÿa-zA-Z]+|[À-ÿa-zA-Z]+|\+{0,1}\-{0,1}\"{1}[À-ÿa-zA-Z]+\s[À-ÿA-Za-z]+', input_q.lower())
   result = []

   for q in query_terms:
       if q.startswith('+"'):
           result.append((q[len('+"'):],4))
       elif q.startswith('-"'):
           result.append((q[len('-"'):],1))
       elif q.startswith("+"):
           result.append((q[len('+'):],4))
       elif q.startswith("-"):
           result.append((q[len('-'):],1))
       elif q.startswith('"'):
           result.append((q[len('"'):],2))
       else:
           result.append((q,2))
   return result

def procesar_ruta_archivo_invertido(rutaArchivoInvertido,prefijo):

    dict_nombre = rutaArchivoInvertido + prefijo +'dict'

    posting_nombre = rutaArchivoInvertido + prefijo + 'posting'

    documentos_nombre = rutaArchivoInvertido + prefijo + 'docs'

    normas_nombre = rutaArchivoInvertido + prefijo + 'norms'

    return dict_nombre, posting_nombre, documentos_nombre, normas_nombre



# ****************************************************************************************  

#indexar('C:/TEC/Flora-20160122/','C:/TEC/index/', "PR1-")

def indexar(rutaColeccion, rutaArchivoInvertido, prefijo):

    rutaColeccion += '*xml'

    dict_nombre,posting_nombre,documentos_nombre,normas_nombre = procesar_ruta_archivo_invertido(rutaArchivoInvertido,prefijo)

    documento_t,documents, words = parseXML_Files(rutaColeccion)
    inverted = term_to_docs(documents, words)
    dictionary, posting,norms = create_inverted_index(documents, inverted)

    save_to_file(dictionary, dict_nombre)
    save_to_file(posting, posting_nombre)
    save_to_file(norms, normas_nombre)
    save_to_file(documento_t, documentos_nombre)

#consultar('C:/TEC/index/', 'PR1-','lustroso')
    
def consultar(rutaArchivoInvertido, prefijo, consulta):

    dict_nombre,posting_nombre,documentos_nombre,normas_nombre = procesar_ruta_archivo_invertido(rutaArchivoInvertido,prefijo)

    dictionary = load_obj(dict_nombre)
    posting = load_obj(posting_nombre)
    norms = load_obj(normas_nombre)
    documento_t = load_obj(documentos_nombre)

    if consulta.endswith('*'):

       query = process_prefix_query(consulta[:len(consulta)-1], dictionary)
       
    else:
       
       query = scan_query(consulta)

    sim,ranking = search(dictionary, posting, query, list(documento_t.keys()),norms)

    res = []
    for i in sorted(ranking, key=lambda x: x[1], reverse=True):

       res.append((documento_t[i[0]],i[0],i[1]))

    save_to_file(res,rutaArchivoInvertido + prefijo + "-rank")


    save_to_html(res, consulta, rutaArchivoInvertido, prefijo)




#buscar('C:/TEC/index/PR1--rank','Q1e-','hojas arrangement espiraladas' )
def buscar(rutaEscalafon, prefijo, clausulas):

   clausulas = clausulas.replace(',','\n')
   
   rutaDocumentos = 'C:/TEC/index/PR1-docs'

   ranking = load_obj(rutaEscalafon)
   
   structuredQueries(ranking,prefijo, rutaEscalafon, clausulas)
