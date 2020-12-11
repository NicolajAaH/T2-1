# T2-1

OBS. DET KRÆVER MAN HAR JAVA 15 SE INSTALLERET PÅ SIN COMPUTER. DET KAN HENTES HER:
https://www.oracle.com/java/technologies/javase-jdk15-downloads.html

1. Kør den der hedder run_mac_linux.command hvis du kører på linux eller MacOS/OS X. Er du på Windows, så kør den der hedder run_windows.bat.
2. Spillet skulle gerne starte

HVIS de ikke virker så åben en konsol i mappen og kopier følgende ind:
java --module-path "./javafx-sdk-15.0.1/lib" --add-modules javafx.controls,javafx.fxml -jar energy.jar

Hvis det heller ikke virker gå ind på følgende:
https://gluonhq.com/products/javafx/
Scroll ned til "latest release" og find den der passer til din computer. Download den og lig den et sted hvor den kan findes. Kopier stien til denne mappe og erstat det i koden ovenover, og gør det samme som beskrevet. Den skal være ind til lib mappen! Altså:

java --module-path "DIN-STI-HER/javafx-sdk-15.0.1/lib" --add-modules javafx.controls,javafx.fxml -jar energy.jar
