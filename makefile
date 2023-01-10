ALP.class: ALP.java
	javac -g ALP.java

clean:
	rm *.class

run: ALP.class
	java ALP