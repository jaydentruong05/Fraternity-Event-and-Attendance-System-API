# Deployment checklist

1. Provision PostgreSQL and create a dedicated application user.
2. Copy `.env.example` into the hosting provider's secret/environment settings.
3. Set `SPRING_PROFILES_ACTIVE=prod` and replace every placeholder value.
4. Set `APP_CORS_ALLOWED_ORIGINS` to the exact HTTPS frontend origin.
5. Deploy the backend with the included `Dockerfile`.
6. Set the frontend's `VITE_API_URL` to the backend's public HTTPS URL and build it.
7. Run `scripts/backup-db.sh` on a daily schedule and retain encrypted off-site copies.
8. Test restoring one backup before inviting users.
9. Confirm HTTPS is enforced by the hosting provider.
10. Rotate the officer password whenever an officer who knew it leaves office.

The production profile deliberately has no credential defaults. The application will refuse
to start when required production environment variables are missing.
