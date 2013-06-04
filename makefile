#
# to compile everything: make all
#
# to init the environment: make init
# (oly once per session per station)
#
# to start the server: make srv
#
# to start the client: make cli
#
# to stop everything: make kill
#

all: clean idl compile

idl:
	mkdir generated 
	idlj -td generated -emitAll -fall banque.idl
	idlj -td generated -emitAll -fall banqueAdmin.idl

compile:
	mkdir classes
	javac -d classes/ generated/*.java src/*.java

init:
	orbd -ORBInitialPort 12345 &
clean:
	rm -rf generated/ classes/

srv:
	java -classpath .:./classes Server > ior.txt &

cliAdmin:
	java -classpath .:./classes ClientAdmin `cat ior.txt` -ORBInitialPort 12345

cliConsult:
	java -classpath .:./classes ClientConsult `cat ior.txt` -ORBInitialPort 12345

kill:
	killall java
	killall orbd
