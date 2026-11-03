# Incident #006 — Disk Space Full Investigation

## Environment
- Platform: Ubuntu Linux (WSL2)
- Filesystem: ext4 on a 50 MB loopback image
- Mount point: `~/projects/production-support-lab/disk-lab/mnt`
- Scope: Isolated test filesystem; no production systems affected

## Incident Summary
A simulated application write operation failed with `No space left on device` after the test filesystem reached 100% utilization.

## Impact
- New writes to the test filesystem failed.
- The host Ubuntu filesystem was not affected.

## Investigation
1. Created a 50 MB image using `fallocate` and formatted it with `mkfs.ext4`.
2. Mounted the image using `mount -o loop`.
3. Verified the available space using `df -h` (approximately 40 MB available).
4. Attempted to write 60 MB using `dd`, which failed with `No space left on device`.
5. Ran `df -h` again and confirmed 100% utilization, with 0 MB available.
6. Used `du -ah | sort -hr | head -n 10` to identify the largest files.
7. Found `test-file.bin` consuming approximately 41 MB.

## Root Cause
The test write operation exhausted the available space on the isolated ext4 filesystem. The partially written `test-file.bin` occupied most of its usable capacity.

## Resolution
- Removed the generated test file.
- Rechecked filesystem usage with `df -h`.
- Confirmed utilization dropped from 100% to 1%, with approximately 40 MB available.
- Unmounted the test filesystem and removed the temporary disk image.

## Prevention and Lessons Learned
- Monitor disk utilization and configure alerts before capacity is exhausted.
- Investigate large files and directories using `df` and `du`.
- Verify file ownership and application dependencies before deleting anything in production.
- Consider log rotation, retention policies, and capacity planning.
- Confirm recovery with a fresh disk-usage check and, in production, an application-level write test.

## Outcome
The simulated disk-full incident was resolved and the test environment was cleaned up.
