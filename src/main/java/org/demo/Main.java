/*******************************************************************************
 * Copyright (c) 2016, Xavier Miret Andres <xavier.mires@gmail.com>
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
package org.demo;

import org.demo.db.H2;
import org.demo.model.UserTask;
import org.demo.model.UserTask.Status;
import org.demo.model.UserTaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application entry point.
 */
@SpringBootApplication
@ComponentScan({"org.demo"})
public class Main
{
    public static void main(String... params) throws Exception
    {
        // init H2.
        H2.init();

        // Spring start.
        SpringApplication.run(Main.class, params);
    }

    @Bean
    public CommandLineRunner initDb(UserTaskService userTaskService)
    {
        return (args) ->
        {
            userTaskService.save(UserTask.create("Orange Festival", "John", "Hotel.", Status.PENDING,
                    "Mid range. Breakfast included. Close to the festival."));
            userTaskService.save(UserTask.create("Orange Festival", "Audrey", "Train tickets.", Status.PENDING,
                    "Depart on Friday (we should be there at 16h), return on Sunday night or Monday morning."));
            userTaskService.save(UserTask.create("Orange Festival", "-", "Snacks for the ride.", Status.PENDING,
                    "Some chips and beer do the trick."));
            userTaskService.save(UserTask.create("Orange Festival", "-", "Guide.", Status.PENDING,
                    "Pick up some Warsaw city guide, or some printouts."));
        };
    }
}
