# Introduction #

Using the Proof of Concept code. Source Path: svn/trunk/PLT/src/ProofOfConcept/


# Details #

Try not to use Eclipse to do this.

1. Compile and run the two java files which are inside the ProofOfConcept folder. You may need to modify the path passed to reloadClass() in main().

2. Make sure that the subfolder called test is not in the class path.

3. Step 1 should display a error with a path saying that RobotReloadableClassImpl was not found or something. Let this program run.

4. Compile the file RobotReloadableClassImpl.java with RobotReloadableClass.java in the class path

5. Check to see if the program in step 3 can find the class now. It should run call the think() and print the code. Keep the program running.

6. Make modifications to code in RobotReloadableClassImpl.java and recompile. The program should print the new code in the next iteration.