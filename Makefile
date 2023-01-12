run: compile
	@echo java Frontend $(ARGS)
	
compile: Character.class test Backend.class Frontend.class
	

Character.class: Character.java CharacterDataReader.java CharacterInterface.java CharacterDummy.java
	@echo javac Character.java
	@echo javac CharacterDataReader.java
	@echo javac CharacterInterface.java
	@echo javac CharacterDummy.java


Backend.class: Backend.java BackendDummy.java BackendInterface.java BackEndInterfaceDummy.java SortedCollectionInterface.java RedBlackTree.java
	@echo javac Backend.java
	@echo javac BackendDummy.java
	@echo javac BackendInterface.java
	@echo javac BackEndInterfaceDummy.java
	@echo javac SortedCollectionInterface.java
	@echo javac RedBlackTree.java
	
	
Frontend.class: Frontend.java
	@echo javac Frontend.java



test: testData testBackend testFrontend

testData: DataWranglerTests.java
	@echo javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --scan-classpath

testBackend: BackEndDeveloperTests.java 
	@echo javac -cp .:junit5.jar BackEndDeveloperTests.java
	java -jar junit5.jar -cp . --scan-classpath

testFrontend: FrontEndDeveloperTests.java 
	@echo javac -cp .:junit5.jar FrontEndDeveloperTests.java
	java -jar junit5.jar -cp . --scan-classpath


clean: 
	$(RM) *.class
	$(RM) *~



