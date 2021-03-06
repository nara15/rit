
# Library for processing XML files
import xml.etree.ElementTree as ET
# Library for reading files sequentially
from glob import glob
# Library for using regular expressions over text
import re

def getTaxonDescription(description):

    description = description.split()

    if len(description) > 2:

        return " ".join(description[:2])
    
    else:
        
        return " ".join(description)
    

"""
    This functions parses every XML file from the repository and retrieves
    the needed parts of the files to index.
    It returns
               * a dict of documents and its content
               * a set of all the text found in documents
"""

def parseXML_Files(fileglob='G:/TEC/Flora-20160122/*.xml'):
    
    ns='http://www.github.com/inbio'
    
    documents, words = {}, set()
    documento_t = {}
    
    for xmlFile in glob(fileglob):
        
        tree = ET.parse(xmlFile)
        
        taxon_identification = tree.find("{%s}taxon_identification" %(ns)).attrib
        
        description = tree.find("{%s}description" %(ns)).attrib
        
        des = description.get("taxon_description")
        texto = re.findall(r'[À-ÿa-zA-Z]+|\d+\,{0,1}\d|\d', des.lower())
        texto.append(taxon_identification.get("rank"))
        texto.append(getTaxonDescription(taxon_identification.get("taxon_name")).lower())
        
        words |= set(texto)

        docId = xmlFile.split('\\')[-1]
        documento_t[docId] = xmlFile
        documents[docId] = texto
        
    return documento_t,documents, words
