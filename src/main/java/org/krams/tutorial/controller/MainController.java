package org.krams.tutorial.controller;

import org.springframework.validation.BindingResult;
import org.apache.log4j.Logger;
import org.krams.tutorial.domain.Person;
import org.krams.tutorial.service.PersonService;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves person request
 * Управляет и возвращает запрос
 */
@Controller
@RequestMapping("/")
public class MainController {
    private String filtr="";
    protected static Logger logger = Logger.getLogger("controller");
    List<Person> persons; //sublist for paging purpose
    PagedListHolder<Person> pagedListHolder; //pagination class
    int currentPageNumber=0; //номер текущей страницы

    private void initList(){
        currentPageNumber=0;
        filtr="";
    }

    @Resource(name="personService")
    private PersonService personService;

    /**
     * Handles and retrieves all persons and show it in a JSP page
     * Получает всех персон и показывает их на jsp
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPersons(
            Model model) {

        logger.debug("Received request to show all persons");

        // Retrieve all persons by delegating the call to PersonService
        // Получает всех персон вызовом PersonService
        List<Person> personsAll = personService.getAll(filtr);
        pagedListHolder = new PagedListHolder<Person>(personsAll);
        pagedListHolder.setPageSize(5);

        currentPageNumber = pagedListHolder.getPageCount()<currentPageNumber?
                pagedListHolder.getPageCount() -1: currentPageNumber;
        pagedListHolder.setPage(currentPageNumber);

        persons = pagedListHolder.getPageList();
        // Attach persons to the Model
        // Прикрепляет персон к модели
        model.addAttribute("persons", persons);
        model.addAttribute("personAttribute", new Person());
        model.addAttribute("pageNumber", pagedListHolder.getPage()+1);
        model.addAttribute("pageCount", pagedListHolder.getPageCount());

        // This will resolve to /WEB-INF/jsp/personspage.jsp
        // Перенаправляет на /WEB-INF/jsp/personspage.jsp
        return "personspage";
    }

    /**
     * Retrieves the add page
     * Возвращает страницу Добавления
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Create new Person and add to model
        // This is the formBackingOBject
        // Создает новую персону и добавляет ее в модель
        // Это возвращающая форма
        model.addAttribute("personAttribute", new Person());
        //getPersons(model);
        // This will resolve to /WEB-INF/jsp/addpage.jsp
        // перенаправление на страницу Добавления
        return "addpage";
    }

    /**
     * Adds a new person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     * Добавляет новую персону через PersonService
     * Показывает подтверждающую JSP
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("personAttribute") Person person, BindingResult result, Model model)
        {logger.debug("Received request to add new person");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name
        // Call PersonService to do the actual adding
        // модель "personAttribute" передана контроллеру из JSP
        // мы используем имя "personAttribute" потому что JSP  страница использует его
        // Вызов PersonService для совершения добавления
        personService.add(person);
        initList();
        getPersons(model);
        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "personspage";//"addedpage";
    }

    /**
     * Deletes an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id,
                         Model model) {

        logger.debug("Received request to delete existing person");

        // Call PersonService to do the actual deleting
        personService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);
        initList();
        getPersons(model);
        // This will resolve to /WEB-INF/jsp/deletedpage.jsp
        return "personspage";
    }



    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findList(@ModelAttribute("personAttribute") Person person,
                           @RequestParam(value="name", required=true) String pers,
                           Model model) {
        //model.addAttribute("name", pers);
        filtr=person.getName();
        if(filtr.isEmpty()) {
            initList();
        }
        /*List<Person> personsAll = personService.getAll(filtr);

        pagedListHolder = new PagedListHolder<Person>(personsAll);
        pagedListHolder.setPageSize(2);
        persons = pagedListHolder.getPageList();*/
        getPersons(model);
        return "personspage"; //"editedpage"
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public String nextPage(Model model) {
        currentPageNumber++;
        getPersons(model);
        return "personspage";
    }


    @RequestMapping(value = "/prev", method = RequestMethod.GET)
    public String prevPage(Model model) {
        if( currentPageNumber > 0 ) {
            currentPageNumber--;
        }
        getPersons(model);
        return "personspage";
    }


    /**
     * Retrieves the edit page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Integer id,
                          Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing Person and add to model
        // This is the formBackingOBject
        model.addAttribute("personAttribute", personService.get(id));

        // This will resolve to /WEB-INF/jsp/editpage.jsp
        return "editpage";
    }

    /**
     * Edits an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("personAttribute") Person person,
                           @RequestParam(value="id", required=true) Integer id,
                           Model model) {
        logger.debug("Received request to update person");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name

        // We manually assign the id because we disabled it in the JSP page
        // When a field is disabled it will not be included in the ModelAttribute
        person.setId(id);

        // Delegate to PersonService for editing
        personService.edit(person);

        // Add id reference to Model
        model.addAttribute("id", id);
        initList();
        getPersons(model);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "personspage"; //"editedpage"
    }

}