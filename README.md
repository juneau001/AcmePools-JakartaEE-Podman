AcmePools-Podman - Jakarta EE 10 and Podman Example
================

PrimeFaces Enterprise Application Example running on Jakarta EE 10.

- Postgresql database
- Docker/Podman
- Flyway for database initialization
- Payara Micro

To run the example, ensure that Docker or Podman is installed on local machine,
and then navigate to the root directory and issue the command:

<pre>
podman compose up
</pre>
or
<pre>
docker compose up
</pre>

Note: If using Podman this project must be installed into a volume
to which Podman has access.  For instance, to provide Podman access
to the home directory on OS X, create a podman machine as follows:

<pre>
podman machine init -v $HOME:$HOME
</pre>

Once the Podman machine has been started, ensure that this project resides
somewhere within the $HOME and then issue the 'podman compose up' command.

No @EJB Required!
===============

This project is an example of using Jakarta Persistence without EJB. Rather
than using EJB, this project utilizes CDI only for performing transactions
against the Postgresql database.