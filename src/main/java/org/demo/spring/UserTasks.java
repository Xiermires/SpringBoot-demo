/*******************************************************************************
 * Copyright (c) 2017, Xavier Miret Andres <xavier.mires@gmail.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted, provided that the above 
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES 
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALLIMPLIED WARRANTIES OF 
 * MERCHANTABILITY  AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR 
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES 
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN 
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF 
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *******************************************************************************/
package org.demo.spring;

import java.util.Collection;

import org.demo.model.UserTask;
import org.demo.model.UserTaskService;
import org.demo.model.UserTaskService.InvalidAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userTasks")
public class UserTasks
{
    @Autowired
    private UserTaskService uts;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UserTask>> getAllUserTasks()
    {
        return new ResponseEntity<>(uts.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public ResponseEntity<Collection<UserTask>> getUserTaskByName(@PathVariable String userName)
    {
        return new ResponseEntity<>(uts.findByUserName(userName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserTask> upsertUserTask(@RequestBody UserTask userTask)
    {
        try
        {
            return new ResponseEntity<>(uts.save(userTask), HttpStatus.CREATED);
        }
        catch (InvalidAssign e)
        {
            return new ResponseEntity<>(userTask, HttpStatus.CONFLICT);
        }
    }
}
