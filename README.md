# Utilisation API gestion-images

## Récuparation d'une image
GET /photos/{identifiant_personne}

Modèle du retour
* Image dans le format en binary-data
* nom de l'image (nom+extension) dans le header <filename>


## Upload d'une image
POST /photos/{identifiant_personne}

Modèle de retour
* Réponse dans le body
