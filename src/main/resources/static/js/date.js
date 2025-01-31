// Récupère l'heure actuelle en millisecondes
var heure = Date.now();

// Calcul des dates de début et de fin de l'enchère (ajout de 5ms et 10ms dans cet exemple)
var dateEnchere = new Date(heure + 5);  // Heure de début de l'enchère
var dateFinEncheres = new Date(heure + 10);  // Heure de fin de l'enchère

// Sélection des éléments où tu veux afficher les dates
var dateElement = document.getElementById('date');
var dateEnchereElement = document.getElementById('dateEnchere');
var dateFinEncheresElement = document.getElementById('dateFinEnchere');

// Affichage des dates formatées dans les éléments HTML
dateElement.innerText = "Heure actuelle : " + new Date(heure).toLocaleString();
dateEnchereElement.innerText = "Début de l'enchère : " + dateEnchere.toLocaleString();
dateFinEncheresElement.innerText = "Fin de l'enchère : " + dateFinEncheres.toLocaleString();
