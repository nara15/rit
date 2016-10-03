# coding=UTF-8
# Library for processing XML files
import xml.etree.ElementTree as ET
# Library for using regular expressions over text
import re
import datetime

"""
This function improves vector space queries result by conditions over the gained
 files structure searching sequentially through these files for specific data.

"""
# Must receive path,ranking and query for improving ranking
def structuredQueries(pRanking,pPrefijo="TP1-", pRankingPath="", pQueries = ""):
    #pRanking = [('478-Klaprothia fasciculata.xml', 0.1726670859381201), ('543-Calatola costaricensis.xml', 0.11084272481421198), ('547-Leretia cordata.xml', 0.10583586109558445), ('224-Magnolia sororum Seibert.xml', 0.10764146926445299), ('551-Oecopetalum greenmanii.xml', 0.17993655503379416), ('685-Lennoa madreporoides.xml', 0.07098280139850777), ('570-Lauraceae.xml', 0.049596695804410174), ('223-Magnolia poasana (Pittier).xml', 0.11303834637963545), ('209-Eschweilera calyculata Pittier.xml', 0.10556288449673286), ('691-Lennoa madreporoides.xml', 0.07098280139850777)]
    now = datetime.datetime.now()

    # data is an html file with the query info
    data = "<!DOCTYPE html>\n<html>\n<body>\n"
    data = data + "<p>" + str(now) +"</p>"+"\n"
    data = data + "<p>" + pRankingPath +"</p>\n"


    result = {}
    resultAllQueries = {}

    ns='http://www.github.com/inbio'

    # queries
    #pQueries = "hojas arrangement espiraladas"
    #pQueries = "hojas arrangement espiraladas\nflores coloration blancas"
    #pQueries = "ramitas pubescence glabras"
    #pQueries = "ramitas pubescence glabras\nramitas pubescence glabras\nramitas pubescence glabras\nramitas pubescence glabras"



    pQueries = pQueries.split("\n")

    counter = 0
    #process query
    data = data + "<p> Query </p>\n"
    data = data + "<ul>\n"
    for pQuery in pQueries:
        data = data + "<li>" + pQuery +"</li>\n"
        pQueries[counter] = pQuery.split(" ")
        counter += 1
    data = data + "</ul>\n"
    data = data + "<p>Result:</p>\n"

    queriesNumber = len(pQueries)

    for pQuery in pQueries:

        for i in pRanking:
            
            filePath = i[0]
            #print("\n\n")
            tree = ET.parse(filePath)
            #finds taxon_identification element
            taxon_identification = tree.find("{%s}taxon_identification" %(ns)).attrib

            #finds description element
            description = tree.find("{%s}description" %(ns))
            #finds all statements of description
            statements = description.findall("{%s}statement" %(ns))
            #iterates statements for finding biological_entity
            for statement in statements :
                #finds biological_entity element
                biological_entity = statement.find("{%s}biological_entity" %(ns))
                #finds all characters of biological_entity
                characters = biological_entity.findall("{%s}character" %(ns))
                #iterates characters for finding its name and value
                for character in characters:
                    biological_entity_name = biological_entity.attrib.get("name")
                    character_name = character.attrib.get("name")
                    character_value = character.attrib.get("value")

                    if (pQuery[0] == biological_entity_name) and (pQuery[1] == character_name) and (pQuery[2] == character_value):

                        if result.get(i[0]) == None :
                            #print("i[0]: " + i[0])
                            #print("i[1]: " + str(i[1]))
                            #print("\n")
                            #i[0] name
                            #i[1] weight
                            result[i[0]] = i[1]
                            resultAllQueries[i[0]] = 1

                        else:
                            #result[i[0]] += 1
                            resultAllQueries[i[0]] += 1

    resultSorted = {}
    #print (result)
    for a in resultAllQueries:
        #print("a: " + a + "result[a]: " + str(result[a]) )
        if resultAllQueries[a] == queriesNumber :
            print("This is a: " + a)
            resultSorted[a] = a

    sorted(resultSorted.values(),reverse=True)

    data = data + "<ul>\n"
    for a in resultSorted:
        for b in pRanking:
            if b[0] == a:
                data = data +"<li>" + str(b) + "</li>\n"
    data = data + "</ul>\n"

    data = data + "</body>\n</html>"
    print(data)

    f = open(pPrefijo + "result.html", 'w')
    f.write(data)
    f.close()
