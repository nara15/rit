import xml.etree.ElementTree as ET

import dominate
from dominate.tags import *

import datetime



def save_to_html(ranking, query, repo_path, prefijo):

    doc = dominate.document(title='Dominate your HTML')
    ns='http://www.github.com/inbio'
    
    with doc:

        with div():
            attr(cls='body')
            p('Hora y día ' + str(datetime.datetime.now()))
            p('Ruta a la colección: ' + repo_path)
            p('Texto de la consulta: ' + query)
        
        with div(id='header').add(ol()):
            
            for i in ranking:

                tree = ET.parse(i[0])
                taxon_identification = tree.find("{%s}taxon_identification" %(ns)).attrib
                rank = taxon_identification.get("rank")
                name = taxon_identification.get("taxon_name")

                description = tree.find("{%s}description" %(ns)).attrib
                des = description.get("taxon_description")
                
                li(i[0].title() + " | " + i[1].title() + " | " + str(i[2]).title())
                li(rank.title() + " ----- " + name.title())
                li(des[:200].title())
                li("#################################################".title())

    f = open(prefijo + "result.html", 'w')
    f.write(str(doc))
    f.close()
