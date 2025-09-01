# Poseidon

# But du projet

Le but de ce projet consiste à complétez le back-end d'une application afin de la rendre plus sécurisé.
Pour cette complétion j'ai été amené a :
- Vérifier les liens et inputs de fichier html afin de validé leurs bon fonctionnement.
- Implémenter la méthode d'authentification.
- Mettre en place le CRUD pour la liste des objets de l'application.
- Mettre en place une batterie de test unitaire concernant les méthodes implémenter.

----------------------------------------------------------------------------------------------------------------------------------------------------------

# Stack technique

1. Spring Boot 3.3.4
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1
5. Jacoco 0.8.12
6. Junit 5.11.3
7. Maven 3.0.0
8. Lombok 1.18.34

----------------------------------------------------------------------------------------------------------------------------------------------------------

# Lancer l'application

Pour commencer lancer les scripts SQL que vous pouvais retrouver dans les chemins src/main/resources/demo.sql et src/test/resources/test.sql.
Une fois les scripts executer placer vous sur le fichier src/main/java/com/nnk/springboot/Application.java
Lancer l'application avec la commande :
- mvn spring-boot:run

Une fois sur l'application vous pouver :
- Vous connnecter avec les utilisateurs enregistrer
   - Identifiant : admin
   - Mot de passe : Admin123!

  - Identifiant : user
  - Mot de passe : User123!

--------------------------------------------------------------------------------------------------------------------------------------------------------------
