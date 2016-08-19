package org.apache.maven.project;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.handler.manager.ArtifactHandlerManager;
import org.apache.maven.model.Resource;
import org.apache.maven.project.artifact.AttachedArtifact;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.AbstractLogEnabled;

@SuppressWarnings( "deprecation" )
@Component( role = MavenProjectHelper.class )
public class DefaultMavenProjectHelper
    extends AbstractLogEnabled
    implements MavenProjectHelper
{
    @Requirement
    private ArtifactHandlerManager artifactHandlerManager;

    public void attachArtifact( MavenProject project, String artifactType, String artifactClassifier,
                                File artifactFile )
    {
        String type = artifactType;

        ArtifactHandler handler = null;

        if ( type != null )
        {
            handler = artifactHandlerManager.getArtifactHandler( artifactType );
        }

        if ( handler == null )
        {
            handler = artifactHandlerManager.getArtifactHandler( "jar" );
        }

        Artifact artifact = new AttachedArtifact( project.getArtifact(), artifactType, artifactClassifier, handler );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }

    public void attachArtifact( MavenProject project, String artifactType, File artifactFile )
    {
        ArtifactHandler handler = artifactHandlerManager.getArtifactHandler( artifactType );

        Artifact artifact = new AttachedArtifact( project.getArtifact(), artifactType, handler );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }

    public void attachArtifact( MavenProject project, File artifactFile, String artifactClassifier )
    {
        Artifact projectArtifact = project.getArtifact();

        Artifact artifact =
            new AttachedArtifact( projectArtifact, projectArtifact.getType(), artifactClassifier,
                                  projectArtifact.getArtifactHandler() );

        artifact.setFile( artifactFile );
        artifact.setResolved( true );

        attachArtifact( project, artifact );
    }

    public void attachArtifact( MavenProject project, Artifact artifact )
    {
        final int size = project.getAttachedArtifacts().size();
        project.addAttachedArtifact( artifact );

        if ( project.getAttachedArtifacts().size() == size && this.getLogger().isInfoEnabled() )
        {
            this.getLogger().info( String.format( "Replaced artifact %s.", artifact ) );
        }
    }

    public void addResource( MavenProject project, String resourceDirectory, List<String> includes,
                             List<String> excludes )
    {
        Resource resource = new Resource();
        resource.setDirectory( resourceDirectory );
        resource.setIncludes( includes );
        resource.setExcludes( excludes );

        project.addResource( resource );
    }

    public void addTestResource( MavenProject project, String resourceDirectory, List<String> includes,
                                 List<String> excludes )
    {
        Resource resource = new Resource();
        resource.setDirectory( resourceDirectory );
        resource.setIncludes( includes );
        resource.setExcludes( excludes );

        project.addTestResource( resource );
    }

}
