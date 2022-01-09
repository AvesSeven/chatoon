# Chatoon
Chatoon est un blog de chats qui permet à chaque propriétaire de chat, qui le voudrait, d'exposer son chat sur un site internet.


## Comment lancer cette application web ?

Afin de lancer Chatoon, il suffit :

- De lancer le terminal dans le dossier chatoon qui se trouve dans hsqldb-2.5.1/hsqldb et d'indiquer la ligne de commande suivante : "java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 chatBDD". Cela va ainsi permettre de démarrer la partie back de Chatoon ;

- De lancer le projet back via votre IDE favori ;

- De double cliquer sur le fichier "index.html" contenu dans le dossier "public" afin de lancer la partie front (celle-ci va automatiquement se connecter avec la partie back).


## Comment insérer de nouvelles données ?

L'ajout de nouvelles données peut s'effectuer de deux manière différentes, soit via la partie back et un outil tel que Postman, soit via la partie front directement.

Via ces deux parties nous pouvons effectuer les actions suivantes :
- Pour les chats :
    - Ajouter un chat ;
        - Pour la partie back, nous donnons les informations sous le format JSON :

                {
                    "nom": "ChatChat",
                    "description" : "Tout beau",
                    "personne" : {
                        "id": 2
                    }
                }

    - Récupérer l'ensemble des chats ;
    - Récupérer un chat spécifique via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route ;
    - Supprimer un chat via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route ;
    - Ajouter un commentaire sur un chat spécifique :
        - Pour la partie back, l'identifiant  du chat est passé dans le route et nous donnons les informations sous le format JSON :
            
                {
                    "message": "ceci est un nouveau message",
                    "personne": {
                        "id": 4
                    }
                }

    - Modifier partiellement un chat via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :

                {
                    "nom" : "Titi"
                }

    - Modifier la totalité d'un objet chat via son identifiant également :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :

                {
                    "nom": "Titi",
                    "description" : "Titi description",
                    "personne" : {
                        "id": 4
                    }
                }


- Pour les personnes :
    - Ajouter une personne :
        - Pour la partie back, nous donnons les informations sous le format JSON :
                
                {
                    "pseudo": "Toto"
                }

    - Récupérer l'ensemble des personnes ;
    - Récupérer une personne spécifique via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route ;
    - Supprimer une personne via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route ;
    - Modifier partiellement une personne via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :

                {
                    "pseudo": "Doudou",
                    "chats": [],
                    "commentaires": []
                }

    - Modifier la totalité d'un objet personne via son identifiant également :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :

                {
                    "pseudo": "Petit Renard",
                    "chats": [],
                    "commentaires": []
                }


- Pour les commentaires :
    - Supprimer un commentaire via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route ;
    - Modifier partiellement un commentaire via son identifiant :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :
          
                {
                    "message": "Encore une modification",
                    "chat": {
                        "id":2
                    }
                }               
    - Modifier la totalité d'un objet commentaire via son identifiant également :
        - Pour la partie back, l'identifiant est passé dans le route et nous donnons les informations sous le format JSON :

                {
                    "message": "J'ai modifié mon commentaire",
                    "chat": {
                        "id": 3
                    },
                    "personne": {
                        "id": 1
                    }
                }