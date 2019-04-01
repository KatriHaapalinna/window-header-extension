# Window Header Extension
WindowHeaderExtension is an extension for adding custom buttons to the header section of the Window component of Vaadin 7 and 8.

This  Vaadin add-on project is created with in.virit:vaadin-gwt-addon archetype.

## Building and running demo
git clone mvn clean install cd demo mvn jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes
### Version 2.1 for V8
- make custom buttons focusable 
- listen for keyup evet on enter/space
- add aria label for custom buttons 

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Development instructions 

1. Import to your favourite IDE
2. Run main method of the Server class to launch embedded web server that lists all your test UIs at http://localhost:9998
3. Code and test
  * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
  * create browser level and integration tests under src/test/java/
  * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile "browsertests" (mvn verify -Pbrowsertests). If you have a setup for solidly working Selenium driver(s), consider enabling that profile by default.
4. Test also in real world projects, on good real integration test is to *create a separate demo project* using vaadin-archetype-application, build a snapshot release ("mvn install") of the add-on and use the snapshot build in it. Note, that you can save this demo project next to your add-on project and save it to same GIT(or some else SCM) repository, just keep them separated for perfect testing.

## GWT related stuff

* To recompile test widgetset, issue *mvn vaadin:compile*, if you think the widgetset changes are not picked up by Vaadin plugin, do a *mvn clean package* or try with parameter *mvn vaadin:compile -Dgwt.compiler.force=true*
* To use superdevmode, issue "mvn vaadin:run-codeserver" and then just open superdevmode like with any other project


