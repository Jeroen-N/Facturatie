::
:: Batchbestand voor het starten van de IVH5 RMI voorbeelden.
:: Je start de server het handigst vanuit de directory waar de webserver
:: de classes kan vinden. Pas deze zo nodig hieronder aan.
::
cd \xampp\htdocs\facturatie

:: Start java met het juiste classpath
:: Server en registry moeten het zelfde ip adres hebben, die van ons.
:: Client krijgt het ip adres van de ander groep, en viseversa
java -cp .\bin;.\libs\log4j-1.2.17.jar facturatieSysteem.main.Server localhost
 
:: Wanneer je securityproblemen wilt oplossen, voeg dan onderstaande optie aan het command toe.
:: Hiermee krijg je inzicht in alle security instellingen.
::
:: 		-Djava.security.debug=access,failure

@pause