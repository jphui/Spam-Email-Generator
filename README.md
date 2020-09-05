# Email Generator Genie (E.G.G. Man)
(formerly known as Spam-Email-Generator)

Hosted on Heroku [here](https://eggman.ml).

**=== Backstory ===**

In my high school CSIII class, there was only one lab that I was never able to finish, and as a completionist, I decided that I would see it through with the extra time I had being quarantined at home. I looked through my old folders for saved source code but ultimately found nothing. So, I started from square one relearning data structures, polymorphism and especially the forgotten basics of Java syntax. This lab was unique because our CS teacher explained to us the immediate application of the Markov Chain model, notably natural language processing and gene sequencing. What excited me the most, however, was its instrumentation to craft *spam emails*.

Although I completed the [lab](https://github.com/jphui/Markov-Chains) with minor issues, I wanted to take it a step further because I saw it as an opportunity to turn an idea kindled in the think tank of the classroom into a real working product. Ultimately I was excited to learn about and use the tools that bring backend to frontend, something that I was disappointed had never been taught in any course I had taken. And honestly, who *doesn't* want to generate smart, tailored text?

**=== Project Development ===**

Finishing the lab was the easy part, because software development had been drilled into me for the past six years. However, I realized that turning this into a functional webapp would be quite an uphill battle with ***0 experience*** in web development. Despite a lack of direction, I knew two things after asking Reddit and CS-savvy friends: Spring Boot and Heroku.

I quickly understood that Spring Boot was an obvious choice because I needed what I learned was called a *"framework"* for the Java code I had already written and because as a framework, it has an unparalleled ease to get set up. As such, it was relatively easy to pick up after sponging information from some incredibly helpful YouTube tutorials (*cough cough Brandan Jones*), but combined with learning HTML and REST basics from the ground up, it was more time consuming than I had thought. Since most enterprise-grade applications involve the use of a database, I ended up having to add code to mold the shape of my project to conform to the MVC way of thinking.

**=== Database Extension ===**

After a couple weeks of being deployed and working as intended, I *completely overhauled* the website to simply make it feel better. Shortly after, I recalled a suggestion a friend had made about being able to see the last 5 or so generated messages. I thought it was fun idea and would also allow me to see some of the real-world results of my work in addition to sharing some laughs with friends.

...To be continued after successful implementation...
