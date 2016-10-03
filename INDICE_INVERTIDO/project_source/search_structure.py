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
def structuredQueries(pRanking,pPrefijo, pRankingPath, pQueries = ""):

    now = datetime.datetime.now()
    
    # data is an html file with the query info
    data = "<!DOCTYPE html>\n<html>\n<body>\n"
    data = data + "<p>" + str(now) +"</p>"+"\n"
    data = data + "<p>" + pRankingPath +"</p>\n"




    ns='http://www.github.com/inbio'

    # queries
    #pQueries = "hojas arrangement espiraladas"
    #pQueries = "ramitas pubescence glabras\nramitas coloration ennegrecidas"
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

    siCumplen = []
    success = False
    for pQuery in pQueries:

        for i in pRanking:
            filePath = i[0]
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
                    #print(biological_entity_name +" "+ character_name +" "+ character_value)


                    if (pQuery[0] == biological_entity_name) and (pQuery[1] == character_name) and (pQuery[2] == character_value):
                        #successCounter += 1
                        #print(i)

                        success = True;
                        print(i)
                        siCumplen.append(i)


            if success == False:
                """
                data = data + "<p>" + str(success) +"<p/>"+"\n"
                data = data + "</body>\n</html>"
                print(data)


                f = open('result.html', 'w')
                f.write(data)
                f.close()
                return success

                """
            else:
                success = False

    data = data + "<p>" + str(success) +"<p/>"+"\n"
    if success == True:
        data = data + "<ul\n"
        for i in pRanking:
            data = data + "<li>" + str(i) + "</li>\n"
        data = data + "</ul>\n"
    data = data + "<p>Archivos Exitosos</p>\n"
    data = data + "<ul>\n"
    for i in siCumplen:
        data = data + "<li>" + str(i) + "</li>\n"
    data = data + "<ul>\n"
    data = data + "</body>\n</html>"

    f = open(pPrefijo + "result.html", 'w')
    f.write(data)
    f.close()
    return success

